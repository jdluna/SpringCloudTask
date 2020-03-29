pipeline {
  agent any
  tools { 
     jdk 'java11slave' 
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '1'))
  }
  parameters {
      booleanParam(defaultValue: false, description: 'Publish to DockerHub', name: 'PUBLISHIMAGE')
  }  
  environment {
    LOGSTASH = 'nuc:5044'
  }
  stages {
    stage('Git') {
      steps {
        git(url: 'https://github.com/wlanboy/SpringCloudTask.git', branch: 'master')
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean package -Dmaven.test.failure.ignore=true'
      }
    }
    stage('Container') {
      steps {
        sh 'docker build -t cloudtask:latest . --build-arg JAR_FILE=./target/cloudtask-0.0.2-SNAPSHOT.jar'
      }
    }
    stage('Publish') {
      when { expression { params.PUBLISHIMAGE == true } }
      steps {
        withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
          sh 'docker push wlanboy/cloudtask:latest'
        }
      }
    }
  }
  post {
      always {
          echo 'Cleanup Workspace'
          deleteDir() /* clean up our workspace */
      }
  }
}
