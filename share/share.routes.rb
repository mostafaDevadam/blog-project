require_relative '../libs'
#require_relative './share.model'
require_relative '../user/user.index'
require_relative '../blog/blog.index'
require 'sinatra/base'


module ShareHelper

  def create
    "create..."
  end

  def findAll

    "All shares... #{Date.today.to_s}"
  end

  def findOneById
    "One share..."
  end
end


class ShareRouter < Sinatra::Base

  #helpers ShareHelper


  post '/share/:user_id' do |user_id|
    # user_id, blogs[...]

    oid=user_id
    user = User.criteria.find(oid)
    puts "user: #{user._id}"

     data = JSON.parse request.body.read.to_s
     puts data['blogs']
     blogs = Blog.criteria.for_ids(data['blogs'])

    blogs.all.each do |item|
      puts "blog item: #{item.to_json}"
      if user._id
        item.set(share_id: user._id)
      end
    end

    blogs.to_json


  end

  get '/share' do
    shares = Blog.where(:share_id.ne => nil)
    puts "all shares: #{shares.to_json}"
    shares.to_json
  end

  get '/share/:share_id' do |share_id|
    share = Blog.where(:share_id => share_id)
    puts "share: #{share.to_json}"
    share.to_json
  end

end
