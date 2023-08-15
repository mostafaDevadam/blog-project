from mongoengine import connect, document, Document, StringField, ReferenceField, ListField


class User(Document):
    # _id = SequenceField()
    name = StringField(required=True)
    # blogs = ListField(ReferenceField('Blog'))
    shares = ListField(ReferenceField('Blog'))
