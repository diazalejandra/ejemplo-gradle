def build_tool

def output_dir =[:]

pipeline {
    agent any
	
	tools {
		gradle 'gradle'
		maven 'maven'
	}

    parameters {
        choice(name: 'Build_Tool', choices : ['maven','gradle'], description : 'Build tool for this pipeline')
    }


    stages {
        stage('Load script') {
           steps{
              script {
                build_tool = load "${params.Build_Tool}.groovy"
                output_dir["gradle"] = "${WORKSPACE}/build/libs/"
                output_dir["maven"]  = "${WORKSPACE}/build/"
              }
           }
        }

        stage("gradle build & test") {
          when {
            expression {
                  params.Build_Tool == "gradle"
            }
          }
          steps{
            script {
              build_tool.build_test()
            }
          }
        }
        stage("maven build & test") {
          when {
            expression {
                  params.Build_Tool == "maven"
            }
          }
          steps{
            script {
              build_tool.build_test()
            }
          }
        }

        stage('sonar') {
            steps {
                echo 'Sonar scan in progress.....'
                script{
                    def scannerHome = tool 'SonarServer';
                    withSonarQubeEnv(credentialsId:'newtoken',installationName:'SonarServer') {
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                    }
                }
                echo '.....Sonar scan completed'
            }
        }

        stage("maven run"){
           when {
                expression {
                    params.Build_Tool == "maven"
                }
           }

           steps{
                script{
                build_tool.run_app();
                }
           }
        }


        stage("gradle run"){
           when {
                expression {
                    params.Build_Tool == "gradle"
                }
           }

           steps{
                script{
                build_tool.run_app();
                }
           }
        }

        stage('wait service to start') {
           steps{
                timeout(5) {
                    waitUntil {
                        script {
                            def exitCode = sh script:"grep -s Started /tmp/mscovid.log", returnStatus:true
                            return (exitCode == 0);
                        }
                    }
                }
          }
        }

        stage('test api rest') {
           steps{
               echo 'test...'
               sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
          }
        } 
		
		stage('uploadNexus v0.0.1') {
           steps{
            step(
             [$class: 'NexusPublisherBuildStep',
                 nexusInstanceId: 'server-nexus',
                 nexusRepositoryId: 'devops-usach-nexus',
                 packages: [[$class: 'MavenPackage',
                       mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1'],
                       mavenAssetList: [
                          [classifier: '', extension: 'jar', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]
                       ] 
                   ]
                 ]
               ]
             )
           }
        }
    }    
}