require_relative './libs'
require_relative './user/user.index'
require_relative './blog/blog.index'
require_relative './share/share.index'






class AppRouter < Sinatra::Base
  set :port, 5000

  before do
    content_type :json
  end
  # user
  post('/user') { UserRouter.call(env) }
  get('/user') { UserRouter.call(env) }
  get('/user/:_id') { UserRouter.call(env) }
  # blog
  post('/blog/:user_id') {BlogRouter.call(env)}
  get('/blog') {BlogRouter.call(env)}
  get('/blog/all/user/:user_id') {BlogRouter.call(env)}
  get('/blog/:_id') {BlogRouter.call(env)}
  #share
  post('/share/:user_id') {ShareRouter.call(env)}
  get('/share') {ShareRouter.call(env)}
  get('/share/:share_id') {ShareRouter.call(env)}


  after do
    content_type :json
  end

end
