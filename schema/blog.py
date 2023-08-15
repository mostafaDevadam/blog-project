from mongoengine import connect, document, Document, StringField, ReferenceField


class Blog(Document):
    title = StringField(required=True)
    user_id = ReferenceField('User')
