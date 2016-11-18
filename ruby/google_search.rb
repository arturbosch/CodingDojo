require 'rest_client'

class Response
  def initialize(content)
    @content = content
  end

  def body
    @content
  end
end
class Success < Response
end
class Error < Response
end

def http_get(url)
  response = RestClient.get url
  if response.code != 200
    Error.new(response.body)
  end
  Success.new(response.body)
end

puts 'Woot?'
response = http_get('https://www.google.de/search?q=google')
puts response.body