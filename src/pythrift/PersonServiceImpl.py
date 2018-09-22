# _*_coding:utf-8 _*_

from py.thrift.generated import ttypes

class PersonServiceImpl:

	def getPersonByUsername(self, username):
		print "Got client param: " + username
		
		person = ttypes.Person()
		person.username = username
		person.age = 20
		person.married = False

		return person

	def savePerson(self, person):
		print "Got client param: " + str(person)
