def call(String appType) {

    if (appType == 'nodejs') {
        sh 'npm install'
    }

    if (appType == 'maven') {
        sh 'mvn package'
    }

    if (appType == 'python') {
        sh 'echo python build'
    }

    if (appType == 'golang') {
        sh 'go build'
    }

    if (appType == 'angular') {
        sh 'echo angular build'
    }
}