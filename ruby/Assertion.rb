def matches(this, that)
  if this == that
    puts 'SUCCESS'
  else
    puts "FAIL: '#{this}' does not match '#{that}'"
  end
end

matches 5, 5
matches 5, 10
matches 5, ''
