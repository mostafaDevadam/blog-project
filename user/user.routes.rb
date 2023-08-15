require_relative '../libs'
require_relative './user.index'


class UserRouter < Sinatra::Base


  post '/user' do
    data = JSON.parse request.body.read
    new_user = User.create!(data)
    {"msg":"post one user...", data: data, new_user: new_user}.to_json
  end


  get '/user' do
    User.all.to_json
  end

  get '/user/:_id' do |_id|
   # user = User.find(_id)
    #user.to_json
  end


end
