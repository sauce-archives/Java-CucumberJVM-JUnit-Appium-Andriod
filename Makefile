run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_android_emulator_5 test_android_s4_4_4

test_android_emulator_5:
	appiumVersion=1.5.3 deviceName="Android Emulator" deviceOrientation=portrait platformVersion=5.0 platformName=Android mvn install

test_android_s4_4_4:
	appiumVersion=1.5.3 deviceName="Samsung Galaxy S4 Emulator" deviceOrientation=portrait platformVersion=4.4 platformName=Android mvn install
