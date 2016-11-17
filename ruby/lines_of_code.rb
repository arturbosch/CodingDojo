def lines_of_code(path)
  IO.readlines(path)
      .map { |line| line.strip }
      .select { |line| !line.empty? }
      .select { |line| !line.start_with? '#' }
      .select { |line| !line.start_with? 'end' }
      .select { |line| !line.start_with? '}' }
      .count
end

path = './fizzbuzz.rb'
loc = lines_of_code(path)
puts "File: #{path.split('/').last} - LOC: #{loc}"