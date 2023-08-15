
class User
  include Mongoid::Document
  include Mongoid::Timestamps
  #include Origin::Queryable
  include Mongoid::Attributes::Dynamic


  field :name, type: String

  has_many :blogs
  #has_many :shares, class_name: "Blog", inverse_of: "share"
  has_many :blog_share, autosave: true, class_name: "Blog", inverse_of: :unit


  #alias_attribute :share_blogs, :shares


  #has_one: doc_name
  #belongs_to :doc_name

end
