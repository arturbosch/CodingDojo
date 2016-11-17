=begin
  Provides simple loc counting of ruby files. Directories are recursively searched for entries.
  Comments are filtered
=end
class RLoc

  class NoSuchFileError < RuntimeError
  end

  def initialize(path)
    @path = path
  end

  # Starting point for loc counting.
  # Checks if existing path is given and start recursion.
  def count
    total_count = 0
    if File.exist? @path
      if is_ruby_file @path
        total_count += count_file(@path)
      elsif File.directory? @path
        total_count += count_dir(@path)
      end
    else
      raise NoSuchFileError.new 'File does not exist!'
    end
    total_count
  end

  # Recursively checks directories and files.
  def count_dir(directory)
    total_count = 0
    Dir.foreach(directory) { |entry|
      if entry == '.' or entry == '..'
        next
      end
      path = File.join(directory, entry)
      if is_ruby_file(path)
        total_count += count_file(path)
      elsif File.directory? path
        total_count += count_dir(path)
      end
    }
    total_count
  end

  # Is given string a ruby file?
  def is_ruby_file(entry)
    entry.end_with? '.rb' and File.file? entry
  end

  # Counts loc of a given file. Filtering comments after reading the file.
  def count_file(file)
    pre_selected = IO.readlines(file)
                       .map { |line| line.strip }
                       .select { |line| !line.empty? }
                       .select { |line| !line.start_with? '#' }

    begin
      idx = pre_selected.find_index { |line| line.start_with? '=' }
      if idx != nil
        pre_selected = drop_while_comment pre_selected, idx
      end
    end while idx != nil

    pre_selected = pre_selected.reject { |line| line == 'end' }
                       .reject { |line| line == '}' }
    puts "#{File.basename file}: #{pre_selected.size}"
    pre_selected.size
  end

  # Drops multi line comments starting from idx till the end '='.
  # For this the method needs to be called until no '=' are left.
  def drop_while_comment(list, idx)
    new_list = []
    rdy = false
    list.each_index { |item_index|
      if rdy
        new_list << list[item_index]
      end
      if item_index == idx
        next
      end
      if item_index < idx
        new_list << list[item_index]
        next
      end
      if not rdy and !list[item_index].start_with? '='
        next
      end
      if list[item_index].start_with? '=' and item_index != idx
        rdy = true
        next
      end
    }
    new_list
  end

end

class NoPathSpecifiedError < RuntimeError
end

if ARGV.size == 0
  raise NoPathSpecifiedError.new 'No path specified!'
end

loc = RLoc.new ARGV[0]
print "\n", 'Total: ', loc.count, "\n"

