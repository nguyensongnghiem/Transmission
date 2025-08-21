# -*- coding: utf-8 -*-
import pandas as pd
import os
import csv
import sys

def convert_excel_to_csv(excel_file_path, output_dir):
    """
    Chuyển đổi tất cả các sheet từ file Excel sang file CSV.
    
    Các giá trị boolean (True/False) sẽ được chuyển đổi thành chuỗi
    binary literal ('_binary ''\\1''' và '_binary ''\\0''') để tương thích
    với MySQL.
    Các giá trị rỗng sẽ được xử lý để trở thành NULL.
    """
    try:
        # Đọc tất cả các sheet từ file Excel vào một dictionary
        excel_data = pd.read_excel(excel_file_path, sheet_name=None, engine='openpyxl')

        # Tạo thư mục đầu ra nếu chưa tồn tại
        if not os.path.exists(output_dir):
            os.makedirs(output_dir)

        # Lặp qua từng sheet và xử lý
        for sheet_name, df in excel_data.items():
            print(f"Đang xử lý sheet: {sheet_name}...")
            
            # Bước 1: Xác định các cột có kiểu dữ liệu boolean
            boolean_cols = df.select_dtypes(include=['bool']).columns

            # Bước 2: Chuyển đổi các giá trị boolean
            if not boolean_cols.empty:
                df[boolean_cols] = df[boolean_cols].replace({True: "_binary '\\1'", False: "_binary '\\0'"})

            # Bước 3: Thay thế các giá trị rỗng (NaN) bằng chuỗi rỗng
            df = df.fillna('')

            # Tạo đường dẫn file CSV đầu ra
            csv_file_path = os.path.join(output_dir, f'{sheet_name}.csv')

            # Lưu DataFrame đã xử lý vào file CSV
            df.to_csv(csv_file_path, index=False, quoting=csv.QUOTE_NONNUMERIC, encoding='utf-8-sig')

            print(f"Đã lưu sheet '{sheet_name}' thành '{csv_file_path}'")

    except FileNotFoundError:
        print(f"Lỗi: Không tìm thấy file Excel tại đường dẫn '{excel_file_path}'")
    except Exception as e:
        print(f"Đã xảy ra lỗi: {e}")

# --- Cấu hình mới: Sử dụng tham số dòng lệnh ---
if __name__ == "__main__":
    # sys.argv[0] là tên script (excel_to_csv.py)
    # sys.argv[1] là tham số thứ nhất (đường dẫn file Excel)
    # sys.argv[2] là tham số thứ hai (đường dẫn thư mục đầu ra)
    if len(sys.argv) < 3:
        print("Sử dụng: python excel_to_csv.py <đường_dẫn_file_excel> <đường_dẫn_thư_mục_đầu_ra>")
    else:
        input_excel_file = sys.argv[1]
        output_directory = sys.argv[2]
        convert_excel_to_csv(input_excel_file, output_directory)
