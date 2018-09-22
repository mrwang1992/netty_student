# _*_coding:utf-8 _*_

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol 

from py.thrift.generated import PersonService
from py.thrift.generated import ttypes


try:
	tSocket = TSocket.TSocket('localhost', 8899)
	tSocket.setTimeout(600)

	transport = TTransport.TFramedTransport(tSocket)
	protocol = TCompactProtocol.TCompactProtocol(transport)

	client = PersonService.Client(protocol)

	transport.open()

	person = client.getPersonByUsername("zhangsan")

	print person

	print "*=" * 32

	new_person = ttypes.Person()
	new_person.username = u"李四"
	new_person.age = 30
	new_person.married = True

	client.savePerson(new_person)

	transport.close()

except Thrift.TException, tx:
	print '%s' % tx.message





