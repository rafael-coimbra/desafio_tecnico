mkdir -p ~/data/in/
mkdir -p ~/data/out/
cp sample_file_1.dat sample_file_2.dat sample_file_3.dat ~/data/in/
./gradlew clean build
./gradlew run
