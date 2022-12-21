Vagrant.configure("2") do |config|

  # configure VM
  config.vm.box = "bento/ubuntu-16.04"

  # configure provisioning
  config.vm.provision "shell", path: "vagrant/bootstrap.sh"

  # configure network ports
  config.vm.network "forwarded_port", host: 9200, guest: 9200 # Elasticsearch
  config.vm.network "forwarded_port", host: 9300, guest: 9300 # Elasticsearch
  config.vm.network "forwarded_port", host: 5000, guest: 5000 # Logtash
  config.vm.network "forwarded_port", host: 5601, guest: 5601 # Kibana
  config.vm.network "forwarded_port", host: 12345, guest: 12345 # Logstash TCP JSON

  # configure provisioning
  config.vm.synced_folder "./vagrant", "/vagrant"

  config.vm.provider "virtualbox" do |vb|
    vb.cpus = 1
    vb.memory = 4096
    vb.gui = false
    vb.name = "elk"
  end
end
