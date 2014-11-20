package net.authorize.auth;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class AbstractMerchantAuthenticationTest extends TestCase {

    IMerchantAuthentication merchantAuth;



    public void setUp() throws Exception {
        super.setUp();
        merchantAuth = new AbstractMerchantAuthentication();
    }

    public void tearDown() throws Exception {
        merchantAuth = null;
    }

    @Test
    public void testGetNameWithObjectInstance() throws Exception {
        synchronized (this){
            assertTrue("Block level intrinsic lock of the enclosing object instance", true);
        }
    }

    @Test
    public synchronized void testGetNameWithObjectInstanceDefault() throws Exception {
        assertTrue("Method level intrinsic lock of the enclosing object instance", true);
    }

    private final Object mLock = new Object();
    @Test
    public void testGetNameWithOtherObjectInstance() throws Exception {
        synchronized(mLock){
            assertTrue("Block level intrinsic lock with other object instance", true);
        }
    }

    @Test
    public synchronized static void testGetNameWithClassDefault() throws Exception {
        assertTrue("Method level intrinsic lock of the enclosing class instance", true);
    }

    @Test
    protected static void testGetNameWithClassName() throws Exception {
        synchronized(AbstractMerchantAuthenticationTest.class){
            assertTrue("Block level intrinsic lock of the enclosing class instance", true);
        }
    }

    @Test
    protected void testGetNameWithClass() throws Exception {
        synchronized(this.getClass()){
            assertTrue("Block level intrinsic lock of the enclosing class instance", true);
        }
    }

    private ReentrantLock mReentrantLock = new ReentrantLock();
    @Test
    protected void testGetNameWithExplicitLock() throws Exception {
        mReentrantLock.lock();
        try{
            assertTrue("Block level explicit lock of the ReentrantLock", true);
        }
        finally {
            mReentrantLock.unlock();
        }
    }

    int sharedResource;
    private ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock();
    @Test
    public void testWriterLock(){
        mRWLock.writeLock().lock();
        try{
            sharedResource++;
            assertTrue("Block level explicit lock of the WriterLock", true);
        }finally {
            mRWLock.writeLock().unlock();
        }
    }
    @Test
    public int testReaderLock(){
        mRWLock.readLock().lock();
        try{

            assertTrue("Block level explicit lock of the ReaderLock", true);
            return sharedResource;
        }finally {
            mRWLock.readLock().unlock();
        }
    }
}