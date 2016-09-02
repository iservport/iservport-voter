package com.iservport.doc.impl

import akka.Done
import com.iservport.doc.api.Document
import com.lightbend.lagom.javadsl.persistence.PersistentEntity
import com.lightbend.lagom.serialization.Jsonable

sealed trait DocumentCommand extends Jsonable

case class CreateDocument(document: Document) extends PersistentEntity.ReplyType[Done] with DocumentCommand
case class GetDocument() extends PersistentEntity.ReplyType[GetDocumentReply] with DocumentCommand
case class AddVote(voterId: String, voterName: String, voted: Int) extends PersistentEntity.ReplyType[Done] with DocumentCommand

case class GetDocumentReply(document: Option[Document]) extends Jsonable
