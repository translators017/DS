//package co.dataswitch.common;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.Stream;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import reactor.core.publisher.Flux;
//import reactor.util.function.Tuple2;
//
//
//@RestController
//public class SSEController {
//
//	private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
//	
//	@GetMapping("/home/console")
//	public SseEmitter getNewNotification(HttpServletRequest request) {
//		System.out.println("sse emitter");
//		SseEmitter emitter = new SseEmitter();
//		
//			Console console = (Console) request.getSession().getAttribute("console");
//			try {
//				emitter.send("data: "+ console.get() +"\n\n");
//				emitter.complete();
//
//			} catch (IOException e) {
//				emitter.completeWithError(e);
//			}
//		
//		return emitter;
//	}
//
//}