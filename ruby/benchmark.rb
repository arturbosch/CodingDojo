require './lines_of_code.rb'

def measure(block)
  start = Time.now
  block
  Time.now - start
end

def benchmark(block)
  (1..10).map {
    block
  }.inject() { |sum = 0, current|
    sum + current
  } / 10
end

time = benchmark(lines_of_code('./lines_of_code.rb'))
puts "Time: #{time}ms"