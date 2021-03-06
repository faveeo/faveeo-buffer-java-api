/**
 * Jenkins Agent has to be configured by having the latest aws cli
 * apt-get install python-pip
 * pip install --upgrade awscli
 * Specifically for the Jenkins user
 * > aws configure
 * > $(aws ecr get-login --no-include-email --region eu-west-1)
 */

pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
        disableConcurrentBuilds()
    }
    triggers {
      pollSCM('H/1 * * * *')
    }

    environment {
        BUILD_TIMESTAMP = new java.text.SimpleDateFormat('yyyyMMdd-HHmm').format(new Date())
        BUILD_FILENAME = "pipeline-${env.BRANCH_NAME}-${env.BUILD_ID}-${env.BUILD_TIMESTAMP}"
    }
    stages {
        stage('Quality check') {
            steps {
                
                catchError {                    
                    sh ' mvn -Psonarlocal clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false sonar:sonar'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Compile and unit tests') {
            steps {
            	catchError {                    
                	sh 'mvn -U -Dmaven.test.skip=true clean install'
				}
            }
        }        
    }
    post {

        failure {
		 	slackSend color: 'danger', message: "FAILED BUILD <${env.RUN_DISPLAY_URL}|${env.JOB_NAME}${env.BUILD_DISPLAY_NAME}>"
        }
        success {
            /**withAWS(credentials:'jenkins-builds-s3', region:'us-east-1') {
                s3Upload(file:"pipeline/assembly/target/${env.BUILD_FILENAME}.tar.gz", bucket:'com.faveeo.jenkins-builds', path: "${env.BUILD_FILENAME}.tar.gz")
            }*/
            slackSend color: 'good', message: "Successful build <${env.RUN_DISPLAY_URL}|${env.JOB_NAME}${env.BUILD_DISPLAY_NAME}>"
        }
    }
}