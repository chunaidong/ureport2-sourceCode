set mvn_repo_home=G:\maven\repository\
set deploy_tmp_folder=G:\maven\mvn_deploy\
set repositoryId=maven-releases
set repoUrl=http://47.114.154.180:8081/repository/maven-releases/
call mvn clean install

xcopy %mvn_repo_home%com\bstek\ureport\ureport2-parent\2.0.6\ureport2-parent-2.0.6* %deploy_tmp_folder% /y
call mvn deploy:deploy-file -DgroupId=com.bstek.ureport -DartifactId=ureport2-parent -Dversion=2.0.6 -Dpackaging=pom -Dfile=%deploy_tmp_folder%ureport2-parent-2.0.6.pom -Durl=%repoUrl% -DrepositoryId=%repositoryId%
