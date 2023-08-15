
class Share
  include Mongoid::Document
  include Mongoid::Timestamps
  include Mongoid::Attributes::Dynamic

  field :title, type: String

  #belongs_to :user
  #has_many :blogs #, class_name: "Blog", inverse_of: "blogs"
  #embeds_many :blogs, store_as: "blogs"
  #has_and_belongs_to_many :blogs, inverse_of: nil #, validate: false






end
