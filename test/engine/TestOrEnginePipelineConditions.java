package engine;

import org.junit.Test;

import com.engine.EngineData;
import com.engine.EngineException;
import com.engine.EnginePipeline;

@SuppressWarnings("unchecked")
public class TestOrEnginePipelineConditions extends TestParent {
	/**
	 * ***************** General OR Conditions *****************
	 */

	@Test
	public void test_engine_pipeline_False_fail() throws EngineException {
		EngineData<String> data = d();
		new EnginePipeline<String>(ts(t1()), rs(f()), null, data).execute();
		assert data.get("key1") == null;
	}

	@Test
	public void test_engine_pipeline_TrueFalse_pass() throws EngineException {
		EngineData<String> data = d();
		new EnginePipeline<String>(ts(t1()), rs(f(), t()), null, data).execute();
		assert data.get("key1").equals("one");
	}

	@Test
	public void test_engine_pipeline_True_pass() throws EngineException {
		EngineData<String> data = d();
		new EnginePipeline<String>(ts(t1()), rs(t()), null, data).execute();
		assert data.get("key1").equals("one");
	}

	@Test
	public void test_engine_pipeline_TrueFalse_2actions_fail() throws EngineException {
		EngineData<String> data = d();
		new EnginePipeline<String>(ts(t1(), t2()), rs(f(), t()), null, data).execute();
		assert data.get("key1").equals("one");
		assert data.get("key2").equals("two");
	}

}
