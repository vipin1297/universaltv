/*
 * package com.geekyants.ads.controller;
 * 
 * import static org.junit.Assert.assertEquals;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import org.mockito.Mockito; import
 * org.mockito.junit.MockitoJUnitRunner; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity;
 * 
 * import com.geekyants.ads.dto.ResponseDto; import
 * com.geekyants.ads.dto.SlotDetail; import
 * com.geekyants.ads.dto.SlotRequestDto; import
 * com.geekyants.ads.dto.SlotResponseDto; import
 * com.geekyants.ads.exception.InvalidDataException; import
 * com.geekyants.ads.service.SlotService;
 * 
 * @RunWith(MockitoJUnitRunner.Silent.class) public class SlotControllerTest {
 * 
 * @InjectMocks SlotController slotController;
 * 
 * @Mock SlotService slotService;
 * 
 * SlotRequestDto slotRequestDto= null;
 * 
 * ResponseDto responseDto= null;
 * 
 * List<SlotDetail> slotDetails=null;
 * 
 * SlotDetail slotDetail= null;
 * 
 * SlotResponseDto slotResponseDto=null;
 * 
 * 
 * @Before public void before() { slotRequestDto= new SlotRequestDto();
 * slotRequestDto.setFromTime("2020-02-17 04:30:00");
 * slotRequestDto.setToTime("2020-02-17 04:35:00"); responseDto= new
 * ResponseDto(); responseDto.setStatusCode(200);
 * responseDto.setMessage("slot booked"); slotResponseDto= new
 * SlotResponseDto(); slotDetails= new ArrayList<>(); slotDetail = new
 * SlotDetail(); slotDetail.setFromTime("2020-02-17 20:00:00");
 * slotDetail.setToTime("2020-02-17 20:10:00"); slotDetail.setPlanId(1L);
 * slotDetail.setSlotId(1L); slotResponseDto.setStatusCode(200);
 * slotResponseDto.setSlotDetails(slotDetails); }
 * 
 * @Test public void bookSlotTest_positive() {
 * Mockito.when(slotService.bookSlot(1L,
 * slotRequestDto)).thenReturn(responseDto); ResponseEntity<ResponseDto>
 * response=slotController.bookSlot(1L, slotRequestDto);
 * assertEquals(HttpStatus.OK, response.getStatusCode()); }
 * 
 * @Test(expected= InvalidDataException.class) public void
 * bookSlotTest__negative() { ResponseEntity<ResponseDto>
 * response=slotController.bookSlot(null, null);
 * 
 * }
 * 
 * @Test public void getSlotTest_positive() {
 * Mockito.when(slotService.getSlot(1L)).thenReturn(slotResponseDto);
 * ResponseEntity<SlotResponseDto> response=slotController.getSlots(1L);
 * assertEquals(HttpStatus.OK, response.getStatusCode()); }
 * 
 * @Test(expected= InvalidDataException.class) public void
 * getSlotTest__negative() { ResponseEntity<SlotResponseDto>
 * response=slotController.getSlots(null); }
 * 
 * }
 */