package com.nevexis.classes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.concurrent.BrokenBarrierException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.itextpdf.text.DocumentException;

import javassist.Loader.Simple;

@RestController
public class Controller {

	@Autowired
	DatabaseThreads th;
	@Autowired
	Array duplicate;
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StreamingResponseBody> download(String type, final HttpServletResponse response) {
		response.setContentType("applicatoin/"+type);
		response.setHeader("Content-Disposition", "attachment;filename=sample."+type);
		StreamingResponseBody stream = out -> {

			try {
				
				Method m = DatabaseThreads.class.getMethod("writeIn"+type,OutputStream.class );
				m.invoke(th, response.getOutputStream());
//				th.writeInPdf(response.getOutputStream());
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};
		return new ResponseEntity<StreamingResponseBody>(stream, HttpStatus.OK);

	}

	@GetMapping(value = "/reflection", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getInformation() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		  ClassA a=ClassA.class.newInstance();  
		StringBuilder result = new StringBuilder();
		for (Method m : a.getClass().getMethods()) {
			result.append("Method: ");
			result.append(m.getName());
			result.append(m.getReturnType().getName());
			result.append(" With parameters type: ");
			result.append("\n");
			Class<?>[] params = m.getParameterTypes();
			for (int i = 0; i < params.length; i++) {
				result.append(params[i].getTypeName());
				result.append("\n");
			}
		}
		for (Field f : a.getClass().getFields()) {
			result.append("Fields: ");
			result.append(f.getName());
			result.append("Type:");
			result.append(f.getType().getName());
			result.append("\n");
		}
		return result.toString();
	}

	public void t1()
			throws InterruptedException, BrokenBarrierException, IOException, DocumentException, URISyntaxException {
		
	}
	void printRepeating(int arr[], int size)
    {
        int i;
        System.out.println("The repeating elements are : ");
          
        for (i = 0; i < size; i++) {
            int j = Math.abs(arr[i]);
            if (arr[j] >= 0)
                arr[j] = -arr[j];
            else
                System.out.print(j + " ");
        }
    }
  
    // Driver code
    public  void repeated()
    {
        Array duplicate = new Array();
        int arr[] = { 1, 2, 3, 1, 3, 6, 6 };
        int arr_size = arr.length;
  
        duplicate.printRepeating(arr, arr_size);
    }

}
