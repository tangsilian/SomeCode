#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

typedef void (*FUNC_2)(int, int, int);

void* (*sub_1818)();
int main() {
	void *handle;

	FUNC_2 func_2 = NULL;
	FILE *fp;

	//handle = dlopen("/mnt/sdcard/leigangwu.so", RTLD_LAZY);

	if (!handle) {
		printf("1213\n141%s\n", dlerror());
		return -1;
	}

	dlerror();

	func_2 = (FUNC_2) dlsym(handle, "_Z15__gnu_Unwind_18P7_JNIEnvP8_jobject");

	sub_1818 = func_2 - 0xA90;
	sub_1818();

	void* __gnu_Unwind_15 = dlsym(handle, "__gnu_Unwind_15");

	if ((fp = fopen("/mnt/sdcard/masongsong.jar", "wb")) == NULL) {
		printf("\nopenFileError");
	}
	fwrite(__gnu_Unwind_15, 0x55BFu, 1, fp);
	fflush(fp);
	close(fp);

	dlclose(handle);
	exit(EXIT_SUCCESS);
}
