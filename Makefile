run_all_in_parallel:
	make clean_it test_iphone_6_8_4

clean_it:
	mvn clean

test_parallel:
	make -j test_iphone_6_8_4 test_iphone_6_9_1

test_iphone_6_8_4:
	appiumVersion=1.4.16 deviceName="iPhone 6" deviceOrientation=portrait platformVersion=8.4  platformName=iOS mvn install

test_iphone_6_9_1:
	appiumVersion=1.4.16 deviceName="iPhone 6" deviceOrientation=portrait platformVersion=9.1 platformName=iOS mvn install
