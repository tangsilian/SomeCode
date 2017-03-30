base:5F491000+24D3C=5F4B5D3C

.text:00024F7C                 ADD             R7, PC  ; "debug"
.text:00024F7E                 MOV             R5, R0
.text:00024F80                 MOV             R0, R4  ; _JNIEnv *
.text:00024F82                 BL              _Z9parse_dexP7_JNIEnvPx ; parse_dex(_JNIEnv *,long long *)5F4B5F82

内存dex size:606716
5F825000  00 00 00 00 00 00 00 00  00 00 00 00 00 00 00 00  ................
5F825010  64 65 78 0A 30 33 35 00  6C 50 34 D8 82 04 15 79  dex.035.lP4....y


auto fp, begin, end, dexbyte;
fp = fopen("E:\\dump.dex", "wb");
begin = 0x5F825010;
end = begin + 606716;
for ( dexbyte = begin; dexbyte < end; dexbyte ++ )
fputc(Byte(dexbyte), fp);
