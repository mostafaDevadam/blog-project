

require_relative './libs'
#require_relative './app.router'
#require_relative './db.config'
require_relative './imports'

#Mongoid.load!(File.join(File.dirname(__FILE__), 'config', 'mongoid.yml'))

class App < Sinatra::Base
  register Sinatra::Reloader

  puts "App"
  AppRouter.run!

  run!
end
