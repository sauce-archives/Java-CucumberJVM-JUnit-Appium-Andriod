run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_iphone7_10 test_ipad_air_9_3

test_iphone7_10:
	appiumVersion=1.6 deviceName="iPhone 7 Simulator" deviceOrientation=portrait platformVersion=10.0  platformName=iOS mvn install

test_ipad_air_9_3:
	appiumVersion=1.6 deviceName="iPad Air Simulator" deviceOrientation=portrait platformVersion=9.3 platformName=iOS mvn install
