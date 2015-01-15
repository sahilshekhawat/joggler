package project.is.joggler;

import java.util.LinkedList;

import android.os.AsyncTask;
import android.os.Build;

import project.is.joggler.api.APICallListener;
import project.is.joggler.api.APICallTask;
import project.is.joggler.api.request.Request;


public class APICallManager
{
    private LinkedList<APICallTask> mTaskList = new LinkedList<APICallTask>();
    
    
    public APICallManager()
    {

    }


    public void executeTask(Request request, APICallListener listener)
    {
        executeTask(request, listener, 1);
    }
    
    
    public void executeTask(Request request, APICallListener listener, int maxAttempts)
    {
        APICallTask task = new APICallTask(request, listener, maxAttempts);
        mTaskList.add(task);
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            // use AsyncTask.THREAD_POOL_EXECUTOR or AsyncTask.SERIAL_EXECUTOR
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            task.execute();
        }
    }
    
    
    public boolean finishTask(APICallTask task)
    {
        return mTaskList.remove(task);
    }


    public int getTasksCount()
    {
        return mTaskList.size();
    }
    
    
    public boolean hasRunningTask(Class<?> requestClass)
    {
        String className = requestClass.getSimpleName();
        
        for(APICallTask task : mTaskList)
        {
            String taskName = task.getRequest().getClass().getSimpleName();
            if(className.equals(taskName)) return true;
        }
        
        return false;
    }
    
    
    public void cancelAllTasks()
    {
        for(int i=mTaskList.size()-1;i>=0;i--)
        {
            APICallTask task = mTaskList.get(i);
            if(task!=null)
            {
                task.cancel(true);
                mTaskList.remove(task);
            }
        }
    }
    
    
    public void killAllTasks()
    {
        for(int i=mTaskList.size()-1;i>=0;i--)
        {
            APICallTask task = mTaskList.get(i);
            if(task!=null)
            {
                task.kill();
                task.cancel(true);
                mTaskList.remove(task);
            }
        }
    }
    
    
    public void printRunningTasks()
    {
        for(APICallTask task : mTaskList)
        {
            Logcat.d("APICallManager.printRunningTasks(): " + (task==null ? "null" : (task.getRequest().getClass().getSimpleName() + " / " + task.getStatus().toString())));
        }
        
        if(mTaskList.isEmpty()) Logcat.d("APICallManager.printRunningTasks(): empty");
    }
}
