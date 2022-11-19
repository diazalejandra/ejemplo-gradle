pipeline {
    agent any
	
	tools {
		gradle 'gradle',
		maven 'maven'
	}
    stages {
        stage('build & test') {
            steps {
                echo 'Compiling...'
                sh "./gradlew clean compile -e"          
            }
        }

        stage('sonar') {
           steps{
              withSonarQubeEnv(credentialsId:'newtoken',installationName:'SonarServer') { 
					script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew clean verify sonar:sonar \
                                     -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        } else {
                            echo 'Windows OS'
                                bat 'gradlew clean verify sonar:sonar \
                                    -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        }
					}
             }
          }
        } 		
    }    
}