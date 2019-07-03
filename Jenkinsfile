#!/usr/bin/groovy

node('maven') {

    env.CI_PROJECT          = "ci"
    env.DEV_PROJECT         = "datacenter-a" // eventually change to "datacenter-a-dev"
    //env.TEST_PROJECT        = "datacenter-a-test"
    //env.PROD_PROJECT        = "datacenter-a"
    env.APP_NAME            = "fuse-client-app"
    env.API_VERSION         = "1.0"
    env.DEPLOYMENT_VERSION  = "v${API_VERSION}.${BUILD_NUMBER}"

    currentBuild.displayName = "${DEPLOYMENT_VERSION}"
    currentBuild.description = "${APP_NAME}-${API_VERSION}.${BUILD_NUMBER}"

    checkout scm

    stage('Build') {
        sh "mvn -B clean install --settings configuration/settings.xml"
    }

}