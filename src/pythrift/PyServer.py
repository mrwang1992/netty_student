# _*_coding:utf-8 _*_

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol 
from thrift.server import TServer

from py.thrift.generated import PersonService
from py.thrift.generated import ttypes
from py.thrift.generated import PersonService

from PersonServiceImpl import PersonServiceImpl

try:
	personServiceHandler = PersonServiceImpl()
	processor = PersonService.Processor(personServiceHandler)

	serverSocket = TSocket.TServerSocket(port=8899)
   	transportFactory = TTransport.TFramedTransportFactory()
	protocolFactory = TCompactProtocol.TCompactProtocolFactory()

	server = TServer.TSimpleServer(processor, serverSocket, transportFactory, protocolFactory)
	server.serve()
	
except Thrift.TException, tx:
	print '%s' % tx.message
