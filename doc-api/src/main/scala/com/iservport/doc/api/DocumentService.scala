package com.iservport.doc.api

import akka.NotUsed
import com.lightbend.lagom.javadsl.api.ScalaService._
import com.lightbend.lagom.javadsl.api.{Descriptor, Service, ServiceCall}

trait DocumentService extends Service {

  def getDocument(id: String): ServiceCall[NotUsed, Document]

  def createDcoument(): ServiceCall[Document, NotUsed]

  def vote(docId: String): ServiceCall[Voter, NotUsed]

  override def descriptor(): Descriptor = {
    named("docservice").withCalls(
      pathCall("/api/docs/:id", getDocument _),
      namedCall("/api/docs", createDcoument _),
      pathCall("/api/docs/:id/vote", vote _)
    ).withAutoAcl(true)
  }

}
