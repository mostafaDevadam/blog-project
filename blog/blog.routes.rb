require_relative '../libs'
require_relative './blog.model'

class BlogRouter < Sinatra::Base

  post('/blog/:user_id') do |user_id|
    data = JSON.parse request.body.read
    oid = user_id
    user = User.find(oid)

    # create blog in user
    user.blogs.create!(data)
    # merge in user
    user.attributes.merge(
      blogs: user.blogs
    )

    user.to_json

  end

  get '/blog' do
    Blog.all.to_json
  end

  get '/blog/all/user/:user_id' do |user_id|
    oid= user_id
    blogs = Blog.where("user_id" => oid).to_json
  end

  get '/blog/:_id' do |_id|
    blog = Blog.find(_id)
    blog.to_json
  end

end
