#!/usr/bin/ruby -w
# require 'customer.rb'

puts 'Hello, Ruby!'

print <<EOF
  Create a document oO
EOF

# Geht aber IntelliJ sagt rot
# print <<`EOC`
# echo hi
# echo bye
# EOC

class Customer
  $no_customers = 0

  def no_cust
    $no_customers
  end

  def initialize(id, name, addr)
    @id=id
    @name=name
    @addr=addr
    $no_customers = $no_customers + 1
  end

  def display_details
    puts "Customer id #{@id}"
    puts "Customer name #{@name}"
    puts "Customer address #{@addr}"
  end

  def say_hello
    puts 'Hi, my name is ' << @name
  end
end


name = 'Artur'

# Before everything in this file
BEGIN {
  # puts 'Hi, ' + name
}

# After everything in this file
END {
  # puts 'Bye, ' + name
}

# Object creation
cust1=Customer.new('1', name, 'Wisdom Apartments, Ludhiya')
cust2=Customer.new('2', 'Chris', 'New Empire road, Khandala')

# Method calls
cust1.say_hello
cust1.display_details
cust2.say_hello
puts cust1.no_cust

# Interpolation of strings
puts "Multiplication Value : #{24*60*60}"

# lambda with do end
(10..15).each do |n|
  print n, ' '
end

puts "\n"

# if else elsif
x=1
if x > 2
  puts 'x is greater than 2'
elsif x <= 2 and x!=0
  puts 'x is 1'
else
  puts "I can't guess the number"
end

# unless
x=1
# noinspection RubyUnlessWithElseInspection
unless x>2
  puts 'x is less than 2'
else
  puts 'x is greater than 2'
end

# Modifiers after statement
$debug=1
print "debug\n" if $debug

#
$age = 5
case $age
  when 0 .. 2
    puts 'baby'
  when 3 .. 6
    puts 'little child'
  when 7 .. 12
    puts 'child'
  when 13 .. 18
    puts 'youth'
  else
    puts 'adult'
end

(0..5).each { |i|
  puts "Value of local variable is #{i}"
}

$i = 0
$num = 5

while $i < $num do
  puts("Inside the loop i = #{$i}")
  $i +=1
end

# yield block example
def test
  yield 5
  puts 'You are in the method test'
  yield 100
end

test { |i| puts "You are in the block #{i}" }

