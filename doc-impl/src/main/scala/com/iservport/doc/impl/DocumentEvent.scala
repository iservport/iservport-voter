package com.iservport.doc.impl

import java.time.Instant
import java.util.Date

import com.lightbend.lagom.javadsl.persistence.{AggregateEvent, AggregateEventTag}
import com.lightbend.lagom.serialization.Jsonable

object DocumentEvent {
  val Tag = AggregateEventTag.of(classOf[DocumentEvent])
}

sealed trait DocumentEvent extends AggregateEvent[DocumentEvent] with Jsonable {
  override def aggregateTag(): AggregateEventTag[DocumentEvent] = DocumentEvent.Tag
}

case class DocumentCreated(id: String, entityId: String, docCode: String, docName: String, docAbstract: String,
  issueDate: Date, authorId: String, authorName: String, docType: Char, docContent: String) extends DocumentEvent

case class VoteAdded(documentId: String, voterId: String, voterName: String, voted: Int, timestamp: Instant = Instant.now()) extends DocumentEvent
