package oldProxies;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sf.cglib.proxy.Enhancer;

public class OptimisticLockService {
	private Enhancer enhancer;
	private SynchronizationService proxy;
	static private  ReadWriteLock lock = new ReentrantReadWriteLock();
	static private Lock readLock = lock.readLock();
	static private  Lock writeLock = lock.writeLock();

	public void initialize() {
		enhancer = new Enhancer();
		enhancer.setSuperclass(SynchronizationService.class);
		 proxy = (SynchronizationService) enhancer.create();

	}

	public void save(Loadable object) {
		writeLock.lock();
		proxy.save(object);
		writeLock.unlock();

	}
	public void delete(Loadable object) {
		writeLock.lock();
		proxy.delete(object);
		writeLock.unlock();

	}
	public void load(Loadable object) {
		readLock.lock();
		proxy.load(object);
		readLock.unlock();

	}
}
