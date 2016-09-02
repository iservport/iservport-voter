package com.iservport.doc.impl

import com.google.inject.AbstractModule
import com.iservport.doc.api.DocumentService
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport

class DocumentModule extends AbstractModule with ServiceGuiceSupport {
  override protected def configure(): Unit = {
    bindServices(serviceBinding(classOf[DocumentService], classOf[DocumentServiceImpl]))
  }
}
