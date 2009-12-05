
begin
  require 'bones'
rescue LoadError
  abort '### Please install the "bones" gem ###'
end

ensure_in_path 'lib'
require 'aspen/version'

task :default => ['spec:rcov','spec:verify','doc:yard','notes']
task 'gem:release' => ['java:build','spec:rcov','spec:verify','doc:yard']

Bones do
  name 'aspen'
  authors ['Kevin Williams']
  email ['kevwil@gmail.com']
  url 'http://kevwil.github.com/aspen'
  version ENV['VERSION'] || Aspen::VERSION::STRING
  rubyforge.name 'aspen'
  readme_file 'README'
  ignore_file '.gitignore'
  depend_on 'rack'
  depend_on 'g', :development => true
  depend_on 'bones', :development => true
  depend_on 'bones-git', :development => true
  depend_on 'bones-extras', :development => true
  depend_on 'rspec', :development => true
  depend_on 'mocha', :development => true
  depend_on 'rcov', :development => true

  ruby_opts << '-Ilib' << '-rubygems'
  spec.opts << '--color'
  # spec.opts << '--format html:./spec_out.html'
  rcov.threshold 80
  rcov.opts << ['--exclude', 'rcov']
  rcov.opts << ['--exclude', 'mocha']
  rcov.opts << ['--spec-only']
  # rcov.opts << ['--no-html']
  # rcov.opts << ['--text-counts']
  # rcov.opts << ['--text-coverage-diff','FILE']

  use_gmail
  enable_sudo
end


require 'fileutils'

namespace :java do

  desc "clean up java tool output"
  task :clean do

    system "cd javalib;mvn --offline clean;cd .."
    FileUtils.rm Dir.glob('lib/**/*.jar')
  end

  desc "build java code and copy jars to lib folder"
  task :build => :clean do

    system "cd javalib;mvn --offline package;ant;cd .."

  end

end

# EOF
