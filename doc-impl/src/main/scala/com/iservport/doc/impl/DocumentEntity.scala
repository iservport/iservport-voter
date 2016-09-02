package com.iservport.doc.impl

import java.util.{Date, Optional}

import akka.Done
import com.iservport.doc.api.{Document, Voter}
import com.lightbend.lagom.javadsl.persistence.PersistentEntity

class DocumentEntity extends PersistentEntity[DocumentCommand, DocumentEvent, DocumentState] {

  import scala.collection.JavaConverters._
  import scala.compat.java8.OptionConverters._

  override def initialBehavior(snapshotState: Optional[DocumentState]): Behavior = {

    val b = newBehaviorBuilder(snapshotState.orElseGet(() => DocumentState(Option.empty)))

    b.setCommandHandler(classOf[CreateDocument], (cmd: CreateDocument, ctx: CommandContext[Done]) => {
      state.document match {
        case Some(_) =>
          ctx.invalidCommand(s"Document ${entityId} is already created")
          ctx.done()
        case None =>
          // Do persist event
          val d = cmd.document
          val events = DocumentCreated(d.id, d.entityId, d.docCode, d.docName, d.docAbstract, d.issueDate, d.authorId,
            d.authorName, d.docType, d.docContent) +:
            d.voters.map(vote => VoteAdded(d.id, vote.voterId, vote.voterName, vote.voted))
          ctx.thenPersistAll(events.asJava, () => ctx.reply(Done))
      }
    })

    b.setEventHandler(classOf[DocumentCreated], (evt: DocumentCreated) => DocumentState(
      new Document(evt.id, evt.entityId, evt.docCode, evt.docName, evt.docAbstract, evt.issueDate, evt.authorId,
        evt.authorName, evt.docType, evt.docContent))
    )

    b.setCommandHandler(classOf[AddVote], (cmd: AddVote, ctx: CommandContext[Done]) => {
      state.document match {
        case None =>
          ctx.invalidCommand(s"Document ${entityId} is not  created")
          ctx.done()
        case Some(d) =>
          ctx.thenPersist(VoteAdded(d.id, cmd.voterId, cmd.voterName, cmd.voted), (evt: VoteAdded) => ctx.reply(Done))
      }
    })

    b.setEventHandler(classOf[VoteAdded], (evt: VoteAdded) => state.vote(Voter(evt.voterId, evt.voterName, evt.voted)))

    b.setReadOnlyCommandHandler(classOf[GetDocument], (cmd: GetDocument, ctx: ReadOnlyCommandContext[GetDocumentReply]) =>
      ctx.reply(GetDocumentReply(state.document))
    )

    b.build()

  }

}
