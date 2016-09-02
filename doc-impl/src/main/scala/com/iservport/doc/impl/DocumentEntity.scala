package com.iservport.doc.impl

import java.util.Optional

import com.lightbend.lagom.javadsl.persistence.PersistentEntity

class DocumentEntity extends PersistentEntity[DocumentCommand, DocumentEvent, DocumentState] {

  override def initialBehavior(snapshotState: Optional[DocumentState]): Behavior = {

    val b = newBehaviorBuilder(snapshotState.orElseGet(() => DocumentState(Option.empty)))

    // TODO create command and event handlers for user

    b.build()
  }

}
