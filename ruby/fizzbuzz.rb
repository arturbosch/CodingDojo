def fizzbuzz(number)

  def divides(n, by)
    n % by == 0
  end

  def fizz_or_buzz(n)
    if divides(n, 15)
      'fizzbuzz'
    elsif divides(n, 5)
      'buzz'
    elsif divides(n, 3)
      'fizz'
    else
      n
    end
  end

  (1..number).each { |n|
    puts fizz_or_buzz(n)
  }
end

fizzbuzz 100