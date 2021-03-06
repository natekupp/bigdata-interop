/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.hadoop.fs.gcs;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unittest inheriting from GoogleHadoopFileSystemTest which uses a syncable output stream for
 * all writes.
 */
@RunWith(JUnit4.class)
public class GoogleHadoopFileSystemSyncableOutputTest extends GoogleHadoopFileSystemTest {

  @ClassRule
  public static NotInheritableExternalResource storageResource =
      new NotInheritableExternalResource(GoogleHadoopFileSystemSyncableOutputTest.class) {
        @Override
        public void before() throws Throwable {
          GoogleHadoopFileSystemTest.storageResource.before();
          ghfs.getConf()
              .set(
                  GoogleHadoopFileSystemConfiguration.GCS_OUTPUT_STREAM_TYPE.getKey(),
                  "SYNCABLE_COMPOSITE");
        }

        @Override
        public void after() {
          GoogleHadoopFileSystemTest.storageResource.after();
        }
      };

  @Test @Override
  public void testHsync() throws Exception {
    internalTestHsync();
  }
}
