# University Risk Management Suite

University Risk Management Suite is an Enterprise Risk Management (ERM) implementation for universities.

### List of In Use Technologies
* Java 8
* Spring Boot 2
* Spring Security
* Spring Data
* H2 Database (dev)
* PostgreSQL (prod)
* Thymeleaf
* JQuery
* Bootstrap 4

### Requirements
* GNU/Linux Operating System
* GIT
* Java Runtime Environment
* Maven 3

Installing Requirements on Debian/Ubuntu:
```sh
$ sudo apt-get install openjdk-8-jdk git maven
```
Installing Requirements on RHEL/Fedora:
```sh
$ sudo dnf install java-1.8.0-openjdk-devel git maven
```
Installing Requirements on openSUSE:
```sh
$ sudo zypper install java-1_8_0-openjdk git maven
```
Installing Requirements on Arch Linux:
```sh
$ sudo pacman -S jdk-8-openjdk git maven
```

### Running Application
```sh
$ git clone https://gitlab.com/esenbogagnu/urms-web.git
$ cd editor
$ mvn spring-boot:run
```
Application will be available on ```localhost:8080```.

### License

URMS is a free software (as in freedom) under GNU Affero General Public License v3.

A program is free software if the program's users have the four essential freedoms:

0. The freedom to run the program as you wish, for any purpose.
1. The freedom to study how the program works, and change it so it does your computing as you wish..
2. The freedom to redistribute copies so you can help others.
3. The freedom to distribute copies of your modified versions to others.

