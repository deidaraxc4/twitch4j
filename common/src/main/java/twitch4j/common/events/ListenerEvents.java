package twitch4j.common.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class ListenerEvents<E extends Event<E>> {
	private final EventManager manager;

	private final ConcurrentHashMap<Class<E>, Set<IListener<E>>> listeners = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	public void registerListener(IListener<E> listener) {
		Class<E> eventClass = getSubtype(listener.getClass());
		if (listeners.containsKey(eventClass) && listeners.get(eventClass) != null)
			listeners.get(eventClass).add(listener);
		else
			listeners.put(eventClass, new LinkedHashSet<>(Collections.singletonList(listener)));
	}

	@SuppressWarnings("unchecked")
	private <X extends IListener<E>> Class<E> getSubtype(Class<X> listenerClass) {
		return (Class<E>) ((ParameterizedType) listenerClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
	}

	public synchronized void dispatch(E event) {
		listeners.entrySet().stream()
				.filter(e -> e.getKey().isAssignableFrom(event.getClass()))
				.map(Map.Entry::getValue)
				.forEach(listeners ->
						listeners.forEach(listener ->
								listener.handle(event)
						)
				);
	}
}
