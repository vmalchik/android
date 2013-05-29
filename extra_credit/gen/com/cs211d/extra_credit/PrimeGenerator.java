/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Victor\\Desktop\\Word Documents\\android\\extra_credit\\src\\com\\cs211d\\extra_credit\\PrimeGenerator.aidl
 */
package com.cs211d.extra_credit;
public interface PrimeGenerator extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.cs211d.extra_credit.PrimeGenerator
{
private static final java.lang.String DESCRIPTOR = "com.cs211d.extra_credit.PrimeGenerator";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.cs211d.extra_credit.PrimeGenerator interface,
 * generating a proxy if needed.
 */
public static com.cs211d.extra_credit.PrimeGenerator asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.cs211d.extra_credit.PrimeGenerator))) {
return ((com.cs211d.extra_credit.PrimeGenerator)iin);
}
return new com.cs211d.extra_credit.PrimeGenerator.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getRandomPrimes:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _arg2;
_arg2 = data.readInt();
int[] _result = this.getRandomPrimes(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeIntArray(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.cs211d.extra_credit.PrimeGenerator
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public int[] getRandomPrimes(int n, int from, int to) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(n);
_data.writeInt(from);
_data.writeInt(to);
mRemote.transact(Stub.TRANSACTION_getRandomPrimes, _data, _reply, 0);
_reply.readException();
_result = _reply.createIntArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getRandomPrimes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int[] getRandomPrimes(int n, int from, int to) throws android.os.RemoteException;
}
