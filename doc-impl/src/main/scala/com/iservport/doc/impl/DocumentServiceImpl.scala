package com.iservport.doc.impl

import javax.inject.Inject

import akka.{Done, NotUsed}
import com.iservport.doc.api.{Document, DocumentService, Voter}
import com.lightbend.lagom.javadsl.api.ServiceCall
import com.lightbend.lagom.javadsl.api.transport.NotFound
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.javadsl.persistence.cassandra.{CassandraReadSide, CassandraSession}

import scala.concurrent.ExecutionContext

class DocumentServiceImpl @Inject()(
  persistentEntities: PersistentEntityRegistry,
  readSide: CassandraReadSide,
  db: CassandraSession)(implicit ec: ExecutionContext) extends DocumentService {

  // Needed to convert some Scala types to Java
  import converter.ServiceCallConverter._

  persistentEntities.register(classOf[DocumentEntity])

  override def getDocument(id: String): ServiceCall[NotUsed, Document] =
    request =>
      documentEntityRef(id).ask[GetDocumentReply, GetDocument](GetDocument())
        .map(_.document.getOrElse(throw new NotFound(s"document $id not found")))

  override def createDcoument(): ServiceCall[Document, NotUsed] = {
    request =>
      documentEntityRef(request.id).ask[Done, CreateDocument](CreateDocument(request))
  }

  override def vote(docId: String): ServiceCall[Voter, NotUsed] = {
    request =>
      documentEntityRef(docId).ask[Done, AddVote](AddVote(request.voterId, request.voterName, request.voted))
  }

  // helper to retrieve entity reference given a class and id
  private def documentEntityRef(documentId: String) = persistentEntities.refFor(classOf[DocumentEntity], documentId)

}
