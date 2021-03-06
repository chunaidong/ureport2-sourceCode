set mvn_repo_home=G:\maven\repository\
set deploy_tmp_folder=G:\maven\mvn_deploy\
set repositoryId=maven-releases
set repoUrl=http://47.114.154.180:8081/repository/maven-releases/
call mvn clean install


xcopy %mvn_repo_home%com\bstek\ureport\ureport2-console\2.2.9\ureport2-console-2.2.9* %deploy_tmp_folder% /y
call mvn deploy:deploy-file -DgroupId=com.bstek.ureport -DartifactId=ureport2-console -Dversion=2.2.9 -Dpackaging=jar -Dfile=%deploy_tmp_folder%ureport2-console-2.2.9.jar -Durl=%repoUrl% -DrepositoryId=%repositoryId%
call mvn deploy:deploy-file -DgroupId=com.bstek.ureport -DartifactId=ureport2-console -Dversion=2.2.9 -Dpackaging=jar -Dclassifier=sources -Dfile=%deploy_tmp_folder%ureport2-console-2.2.9-sources.jar -Durl=%repoUrl% -DrepositoryId=%repositoryId%
call mvn deploy:deploy-file -DgroupId=com.bstek.ureport -DartifactId=ureport2-console -Dversion=2.2.9 -Dpackaging=pom -Dfile=%deploy_tmp_folder%ureport2-console-2.2.9.pom -Durl=%repoUrl% -DrepositoryId=%repositoryId%
