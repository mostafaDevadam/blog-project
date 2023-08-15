
class Blog
  include Mongoid::Document
  include Mongoid::Timestamps
  include Mongoid::Attributes::Dynamic


  field :title, type: String

  #has_many :blogs
  #has_one: doc_name
  belongs_to :user, optional: true #, foreign_key: :user_id
  belongs_to :unit, class_name: "User", inverse_of: :blog_share ,optional: true, foreign_key: :share_id

  #belongs_to :share #, class_name: "Share", inverse_of: "share"
  #embedded_in :share

end
