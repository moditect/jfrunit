/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 - 2021 The JfrUnit authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.moditect.jfrunit.events;

import org.moditect.jfrunit.JfrEventType;

/**
 *
 */
public class JfrEventTypes {
    public static final JfrEventType ACTIVE_RECORDING = ActiveRecording.INSTANCE;
    public static final JfrEventType ACTIVE_SETTING = ActiveSetting.INSTANCE;
    public static final JfrEventType ALLOCATION_REQUIRING_GC = AllocationRequiringGC.INSTANCE;
    public static final JfrEventType BIASED_LOCK_CLASS_REVOCATION = BiasedLockClassRevocation.INSTANCE;
    public static final JfrEventType BIASED_LOCK_REVOCATION = BiasedLockRevocation.INSTANCE;
    public static final JfrEventType BIASED_LOCK_SELF_REVOCATION = BiasedLockSelfRevocation.INSTANCE;
    public static final JfrEventType BOOLEAN_FLAG = BooleanFlag.INSTANCE;
    public static final JfrEventType BOOLEAN_FLAG_CHANGED = BooleanFlagChanged.INSTANCE;
    public static final JfrEventType C_PU_INFORMATION = CPUInformation.INSTANCE;
    public static final JfrEventType C_PU_LOAD = CPULoad.INSTANCE;
    public static final JfrEventType C_PU_TIME_STAMP_COUNTER = CPUTimeStampCounter.INSTANCE;
    public static final JfrEventType CLASS_DEFINE = ClassDefine.INSTANCE;
    public static final JfrEventType CLASS_LOAD = ClassLoad.INSTANCE;
    public static final JfrEventType CLASS_LOADER_STATISTICS = ClassLoaderStatistics.INSTANCE;
    public static final JfrEventType CLASS_LOADING_STATISTICS = ClassLoadingStatistics.INSTANCE;
    public static final JfrEventType CLASS_REDEFINITION = ClassRedefinition.INSTANCE;
    public static final JfrEventType CLASS_UNLOAD = ClassUnload.INSTANCE;
    public static final JfrEventType CODE_CACHE_CONFIGURATION = CodeCacheConfiguration.INSTANCE;
    public static final JfrEventType CODE_CACHE_FULL = CodeCacheFull.INSTANCE;
    public static final JfrEventType CODE_CACHE_STATISTICS = CodeCacheStatistics.INSTANCE;
    public static final JfrEventType CODE_SWEEPER_CONFIGURATION = CodeSweeperConfiguration.INSTANCE;
    public static final JfrEventType CODE_SWEEPER_STATISTICS = CodeSweeperStatistics.INSTANCE;
    public static final JfrEventType COMPILATION = Compilation.INSTANCE;
    public static final JfrEventType COMPILATION_FAILURE = CompilationFailure.INSTANCE;
    public static final JfrEventType COMPILER_CONFIGURATION = CompilerConfiguration.INSTANCE;
    public static final JfrEventType COMPILER_INLINING = CompilerInlining.INSTANCE;
    public static final JfrEventType COMPILER_PHASE = CompilerPhase.INSTANCE;
    public static final JfrEventType COMPILER_STATISTICS = CompilerStatistics.INSTANCE;
    public static final JfrEventType CONCURRENT_MODE_FAILURE = ConcurrentModeFailure.INSTANCE;
    public static final JfrEventType CONTAINER_CP_UTHROTTLING = ContainerCPUThrottling.INSTANCE;
    public static final JfrEventType CONTAINER_CP_UUSAGE = ContainerCPUUsage.INSTANCE;
    public static final JfrEventType CONTAINER_CONFIGURATION = ContainerConfiguration.INSTANCE;
    public static final JfrEventType CONTAINER_IO_USAGE = ContainerIOUsage.INSTANCE;
    public static final JfrEventType CONTAINER_MEMORY_USAGE = ContainerMemoryUsage.INSTANCE;
    public static final JfrEventType DATA_LOSS = DataLoss.INSTANCE;
    public static final JfrEventType DEOPTIMIZATION = Deoptimization.INSTANCE;
    public static final JfrEventType DESERIALIZATION = Deserialization.INSTANCE;
    public static final JfrEventType DIRECT_BUFFER_STATISTICS = DirectBufferStatistics.INSTANCE;
    public static final JfrEventType DOUBLE_FLAG = DoubleFlag.INSTANCE;
    public static final JfrEventType DOUBLE_FLAG_CHANGED = DoubleFlagChanged.INSTANCE;
    public static final JfrEventType DUMP_REASON = DumpReason.INSTANCE;
    public static final JfrEventType EVACUATION_FAILED = EvacuationFailed.INSTANCE;
    public static final JfrEventType EVACUATION_INFORMATION = EvacuationInformation.INSTANCE;
    public static final JfrEventType EXCEPTION_STATISTICS = ExceptionStatistics.INSTANCE;
    public static final JfrEventType EXECUTE_VM_OPERATION = ExecuteVMOperation.INSTANCE;
    public static final JfrEventType EXECUTION_SAMPLE = ExecutionSample.INSTANCE;
    public static final JfrEventType FILE_FORCE = FileForce.INSTANCE;
    public static final JfrEventType FILE_READ = FileRead.INSTANCE;
    public static final JfrEventType FILE_WRITE = FileWrite.INSTANCE;
    public static final JfrEventType FLUSH = Flush.INSTANCE;
    public static final JfrEventType G1_ADAPTIVE_IH_OP = G1AdaptiveIHOP.INSTANCE;
    public static final JfrEventType G1_BASIC_IH_OP = G1BasicIHOP.INSTANCE;
    public static final JfrEventType G1_EVACUATION_OLD_STATISTICS = G1EvacuationOldStatistics.INSTANCE;
    public static final JfrEventType G1_EVACUATION_YOUNG_STATISTICS = G1EvacuationYoungStatistics.INSTANCE;
    public static final JfrEventType G1_GARBAGE_COLLECTION = G1GarbageCollection.INSTANCE;
    public static final JfrEventType G1_HEAP_REGION_INFORMATION = G1HeapRegionInformation.INSTANCE;
    public static final JfrEventType G1_HEAP_REGION_TYPE_CHANGE = G1HeapRegionTypeChange.INSTANCE;
    public static final JfrEventType G1_HEAP_SUMMARY = G1HeapSummary.INSTANCE;
    public static final JfrEventType G1_MM_U = G1MMU.INSTANCE;
    public static final JfrEventType G_CCONFIGURATION = GCConfiguration.INSTANCE;
    public static final JfrEventType G_CHEAP_CONFIGURATION = GCHeapConfiguration.INSTANCE;
    public static final JfrEventType G_CHEAP_SUMMARY = GCHeapSummary.INSTANCE;
    public static final JfrEventType G_CLOCKER = GCLocker.INSTANCE;
    public static final JfrEventType G_CPHASE_CONCURRENT = GCPhaseConcurrent.INSTANCE;
    public static final JfrEventType G_CPHASE_CONCURRENT_LEVEL1 = GCPhaseConcurrentLevel1.INSTANCE;
    public static final JfrEventType G_CPHASE_PARALLEL = GCPhaseParallel.INSTANCE;
    public static final JfrEventType G_CPHASE_PAUSE = GCPhasePause.INSTANCE;
    public static final JfrEventType G_CPHASE_PAUSE_LEVEL1 = GCPhasePauseLevel1.INSTANCE;
    public static final JfrEventType G_CPHASE_PAUSE_LEVEL2 = GCPhasePauseLevel2.INSTANCE;
    public static final JfrEventType G_CPHASE_PAUSE_LEVEL3 = GCPhasePauseLevel3.INSTANCE;
    public static final JfrEventType G_CPHASE_PAUSE_LEVEL4 = GCPhasePauseLevel4.INSTANCE;
    public static final JfrEventType G_CREFERENCE_STATISTICS = GCReferenceStatistics.INSTANCE;
    public static final JfrEventType G_CSURVIVOR_CONFIGURATION = GCSurvivorConfiguration.INSTANCE;
    public static final JfrEventType G_CT_LA_BCONFIGURATION = GCTLABConfiguration.INSTANCE;
    public static final JfrEventType GARBAGE_COLLECTION = GarbageCollection.INSTANCE;
    public static final JfrEventType HEAP_DUMP = HeapDump.INSTANCE;
    public static final JfrEventType INITIAL_ENVIRONMENT_VARIABLE = InitialEnvironmentVariable.INSTANCE;
    public static final JfrEventType INITIAL_SYSTEM_PROPERTY = InitialSystemProperty.INSTANCE;
    public static final JfrEventType INT_FLAG = IntFlag.INSTANCE;
    public static final JfrEventType INT_FLAG_CHANGED = IntFlagChanged.INSTANCE;
    public static final JfrEventType J_VM_INFORMATION = JVMInformation.INSTANCE;
    public static final JfrEventType JAVA_ERROR_THROW = JavaErrorThrow.INSTANCE;
    public static final JfrEventType JAVA_EXCEPTION_THROW = JavaExceptionThrow.INSTANCE;
    public static final JfrEventType JAVA_MONITOR_ENTER = JavaMonitorEnter.INSTANCE;
    public static final JfrEventType JAVA_MONITOR_INFLATE = JavaMonitorInflate.INSTANCE;
    public static final JfrEventType JAVA_MONITOR_WAIT = JavaMonitorWait.INSTANCE;
    public static final JfrEventType JAVA_THREAD_STATISTICS = JavaThreadStatistics.INSTANCE;
    public static final JfrEventType LOADER_CONSTRAINTS_TABLE_STATISTICS = LoaderConstraintsTableStatistics.INSTANCE;
    public static final JfrEventType LONG_FLAG = LongFlag.INSTANCE;
    public static final JfrEventType LONG_FLAG_CHANGED = LongFlagChanged.INSTANCE;
    public static final JfrEventType METASPACE_ALLOCATION_FAILURE = MetaspaceAllocationFailure.INSTANCE;
    public static final JfrEventType METASPACE_CHUNK_FREE_LIST_SUMMARY = MetaspaceChunkFreeListSummary.INSTANCE;
    public static final JfrEventType METASPACE_GC_THRESHOLD = MetaspaceGCThreshold.INSTANCE;
    public static final JfrEventType METASPACE_OO_M = MetaspaceOOM.INSTANCE;
    public static final JfrEventType METASPACE_SUMMARY = MetaspaceSummary.INSTANCE;
    public static final JfrEventType MODULE_EXPORT = ModuleExport.INSTANCE;
    public static final JfrEventType MODULE_REQUIRE = ModuleRequire.INSTANCE;
    public static final JfrEventType NATIVE_LIBRARY = NativeLibrary.INSTANCE;
    public static final JfrEventType NATIVE_METHOD_SAMPLE = NativeMethodSample.INSTANCE;
    public static final JfrEventType NETWORK_UTILIZATION = NetworkUtilization.INSTANCE;
    public static final JfrEventType O_SINFORMATION = OSInformation.INSTANCE;
    public static final JfrEventType OBJECT_ALLOCATION_IN_NEW_TL_AB = ObjectAllocationInNewTLAB.INSTANCE;
    public static final JfrEventType OBJECT_ALLOCATION_OUTSIDE_TL_AB = ObjectAllocationOutsideTLAB.INSTANCE;
    public static final JfrEventType OBJECT_ALLOCATION_SAMPLE = ObjectAllocationSample.INSTANCE;
    public static final JfrEventType OBJECT_COUNT = ObjectCount.INSTANCE;
    public static final JfrEventType OBJECT_COUNT_AFTER_GC = ObjectCountAfterGC.INSTANCE;
    public static final JfrEventType OLD_GARBAGE_COLLECTION = OldGarbageCollection.INSTANCE;
    public static final JfrEventType OLD_OBJECT_SAMPLE = OldObjectSample.INSTANCE;
    public static final JfrEventType P_SHEAP_SUMMARY = PSHeapSummary.INSTANCE;
    public static final JfrEventType PARALLEL_OLD_GARBAGE_COLLECTION = ParallelOldGarbageCollection.INSTANCE;
    public static final JfrEventType PHYSICAL_MEMORY = PhysicalMemory.INSTANCE;
    public static final JfrEventType PLACEHOLDER_TABLE_STATISTICS = PlaceholderTableStatistics.INSTANCE;
    public static final JfrEventType PROCESS_START = ProcessStart.INSTANCE;
    public static final JfrEventType PROMOTE_OBJECT_IN_NEW_PL_AB = PromoteObjectInNewPLAB.INSTANCE;
    public static final JfrEventType PROMOTE_OBJECT_OUTSIDE_PL_AB = PromoteObjectOutsidePLAB.INSTANCE;
    public static final JfrEventType PROMOTION_FAILED = PromotionFailed.INSTANCE;
    public static final JfrEventType PROTECTION_DOMAIN_CACHE_TABLE_STATISTICS = ProtectionDomainCacheTableStatistics.INSTANCE;
    public static final JfrEventType REDEFINE_CLASSES = RedefineClasses.INSTANCE;
    public static final JfrEventType RESERVED_STACK_ACTIVATION = ReservedStackActivation.INSTANCE;
    public static final JfrEventType RETRANSFORM_CLASSES = RetransformClasses.INSTANCE;
    public static final JfrEventType SAFEPOINT_BEGIN = SafepointBegin.INSTANCE;
    public static final JfrEventType SAFEPOINT_CLEANUP = SafepointCleanup.INSTANCE;
    public static final JfrEventType SAFEPOINT_CLEANUP_TASK = SafepointCleanupTask.INSTANCE;
    public static final JfrEventType SAFEPOINT_END = SafepointEnd.INSTANCE;
    public static final JfrEventType SAFEPOINT_STATE_SYNCHRONIZATION = SafepointStateSynchronization.INSTANCE;
    public static final JfrEventType SECURITY_PROPERTY_MODIFICATION = SecurityPropertyModification.INSTANCE;
    public static final JfrEventType SHENANDOAH_HEAP_REGION_INFORMATION = ShenandoahHeapRegionInformation.INSTANCE;
    public static final JfrEventType SHENANDOAH_HEAP_REGION_STATE_CHANGE = ShenandoahHeapRegionStateChange.INSTANCE;
    public static final JfrEventType SHUTDOWN = Shutdown.INSTANCE;
    public static final JfrEventType SOCKET_READ = SocketRead.INSTANCE;
    public static final JfrEventType SOCKET_WRITE = SocketWrite.INSTANCE;
    public static final JfrEventType STRING_FLAG = StringFlag.INSTANCE;
    public static final JfrEventType STRING_FLAG_CHANGED = StringFlagChanged.INSTANCE;
    public static final JfrEventType STRING_TABLE_STATISTICS = StringTableStatistics.INSTANCE;
    public static final JfrEventType SWEEP_CODE_CACHE = SweepCodeCache.INSTANCE;
    public static final JfrEventType SYMBOL_TABLE_STATISTICS = SymbolTableStatistics.INSTANCE;
    public static final JfrEventType SYNC_ON_VALUE_BASED_CLASS = SyncOnValueBasedClass.INSTANCE;
    public static final JfrEventType SYSTEM_GC = SystemGC.INSTANCE;
    public static final JfrEventType SYSTEM_PROCESS = SystemProcess.INSTANCE;
    public static final JfrEventType T_LS_HANDSHAKE = TLSHandshake.INSTANCE;
    public static final JfrEventType TENURING_DISTRIBUTION = TenuringDistribution.INSTANCE;
    public static final JfrEventType THREAD_ALLOCATION_STATISTICS = ThreadAllocationStatistics.INSTANCE;
    public static final JfrEventType THREAD_CP_ULOAD = ThreadCPULoad.INSTANCE;
    public static final JfrEventType THREAD_CONTEXT_SWITCH_RATE = ThreadContextSwitchRate.INSTANCE;
    public static final JfrEventType THREAD_DUMP = ThreadDump.INSTANCE;
    public static final JfrEventType THREAD_END = ThreadEnd.INSTANCE;
    public static final JfrEventType THREAD_PARK = ThreadPark.INSTANCE;
    public static final JfrEventType THREAD_SLEEP = ThreadSleep.INSTANCE;
    public static final JfrEventType THREAD_START = ThreadStart.INSTANCE;
    public static final JfrEventType UNSIGNED_INT_FLAG = UnsignedIntFlag.INSTANCE;
    public static final JfrEventType UNSIGNED_INT_FLAG_CHANGED = UnsignedIntFlagChanged.INSTANCE;
    public static final JfrEventType UNSIGNED_LONG_FLAG = UnsignedLongFlag.INSTANCE;
    public static final JfrEventType UNSIGNED_LONG_FLAG_CHANGED = UnsignedLongFlagChanged.INSTANCE;
    public static final JfrEventType VIRTUALIZATION_INFORMATION = VirtualizationInformation.INSTANCE;
    public static final JfrEventType X509_CERTIFICATE = X509Certificate.INSTANCE;
    public static final JfrEventType X509_VALIDATION = X509Validation.INSTANCE;
    public static final JfrEventType YOUNG_GARBAGE_COLLECTION = YoungGarbageCollection.INSTANCE;
    public static final JfrEventType YOUNG_GENERATION_CONFIGURATION = YoungGenerationConfiguration.INSTANCE;
    public static final JfrEventType Z_ALLOCATION_STALL = ZAllocationStall.INSTANCE;
    public static final JfrEventType Z_PAGE_ALLOCATION = ZPageAllocation.INSTANCE;
    public static final JfrEventType Z_RELOCATION_SET = ZRelocationSet.INSTANCE;
    public static final JfrEventType Z_RELOCATION_SET_GROUP = ZRelocationSetGroup.INSTANCE;
    public static final JfrEventType Z_STATISTICS_COUNTER = ZStatisticsCounter.INSTANCE;
    public static final JfrEventType Z_STATISTICS_SAMPLER = ZStatisticsSampler.INSTANCE;
    public static final JfrEventType Z_THREAD_PHASE = ZThreadPhase.INSTANCE;
    public static final JfrEventType Z_UNCOMMIT = ZUncommit.INSTANCE;
    public static final JfrEventType Z_UNMAP = ZUnmap.INSTANCE;
}
